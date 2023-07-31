// /*eslint-disable*/
// import { Box } from '@mui/system';
// import { Button, Card, CardActions, CardContent, CardHeader, TextField, Typography } from "@mui/material";
// import React, { useState } from "react";
// import axios from 'axios';
// import { useCookies } from 'react-cookie';
// import { useUserStore } from '../../../stores';

// export default function SignIn() {
//     const [userEmail, setEmail] = useState<string>('');
//     const [userPassword, setPassword] = useState<string>('');

//     const [cookies, setCookies] = useCookies();

//     const { user,setUser } = useUserStore();

//     function signInHandler() {
//         if (userEmail.length === 0 || userPassword.length === 0) {
//             alert('이메일과 비밀번호를 입력하세요.');
//             return;
//         }

//         const data = {
//             userEmail,
//             userPassword
//         };

//         axios.post("http://localhost:8284/api/auth/signIn", data)
//             .then(response => {
//                 const responseData = response.data;
//                 if (!responseData.result) {
//                     console.log('로그인에 실패했습니다');
//                     return;
//                 }
//                 const { token, exprTime, user } = responseData.data;
//                 console.log(token, exprTime, user);
//                 const expires = new Date();
//                 expires.setMilliseconds(expires.getMilliseconds + exprTime);

//                 setCookies('token', token, { expires });
//                 alert(cookies.token);
//                 setUser(user);          
                
//             })
//             .catch(error => {
//                 alert('로그인에 실패했습니다.');
//             });
//     }

//     return ( 
//         <Card sx={{ minWidth: 275, maxWidth: "50vw" }}>
//             <CardHeader title={'로그인'}/>                         
            
            
//             <CardContent>
//                 <Box height={'50vh'}>
                    
//                     <TextField fullWidth 
//                                label="이메일주소" 
//                                type="email" 
//                                variant="standard" 
//                                onChange={(e) => setEmail(e.target.value)} />
//                     <TextField fullWidth 
//                                label="비밀번호" 
//                                type="password" 
//                                variant="standard" 
//                                onChange={(e) => setPassword(e.target.value)} />
//                 </Box>
//             </CardContent>
//             <CardActions>
                
//                 <Button variant="contained" onClick={signInHandler}>로그인</Button>
//             </CardActions>
//         </Card>  
//     )
// }
/*eslint-disable*/
import { Box } from '@mui/system';
import { Button, Card, TextField, Typography } from "@mui/material";
import { useState } from "react";

import { useCookies } from 'react-cookie';
import { useUserStore } from '../../../stores';
import { signInApi } from '../../../apis';

interface Props{
    setAuthView: (authView: boolean) => void,
}

export default function SignIn(props: Props) {
     const [userEmail, setEmail] = useState<string>('');
     const [userPassword, setPassword] = useState<string>('');

     const [cookies, setCookies] = useCookies();

     const { user,setUser } = useUserStore();

     const { setAuthView } = props

    const signInHandler = async() => {
        if (userEmail.length === 0 || userPassword.length === 0) {
            alert('이메일과 비밀번호를 입력하세요.');
            return;
        }

        const data = {
            userEmail,
            userPassword
        };

        const signInResponse = await signInApi(data);

        if(!signInResponse) {alert("로그인에 실패했습니다"); 
            return;
        }
    
        if (!signInResponse.result) {
            console.log('로그인에 실패했습니다');
            return;
        }
        const { token, exprTime, user } = signInResponse.data;
        //console.log(token, exprTime, user);
        const expires = new Date();
        expires.setMilliseconds(expires.getMilliseconds + exprTime);

        setCookies('token', token, { expires });
        //alert(cookies.token);
        setUser(user); 

    };
    return ( 
        <Card sx={{ minWidth: 275, maxWidth: "50vw",padding:5 }}>
        <Box>
            <Typography variant='h5'>로그인</Typography>
        </Box>

                <Box height={'50vh'} >                    
                    <TextField fullWidth 
                               label="이메일주소" 
                               type="email" 
                               variant="standard" 
                               onChange={(e) => setEmail(e.target.value)} />
                    <TextField fullWidth 
                               label="비밀번호" 
                               type="password" 
                               variant="standard" 
                               onChange={(e) => setPassword(e.target.value)} />
                </Box>                    
                <Box component='div'>               
                    <Button fullWidth onClick={() => signInHandler()}           variant="contained"> 
                        로그인
                    </Button>
                </Box> 
                <Box component='div' display='flex' mt={2}>
                    <Typography>신규사용자 이신가요?</Typography>
                    <Typography fontWeight={800} ml={1} onClick={() => setAuthView(true)}>
                        회원가입
                    </Typography>
                </Box>            
        </Card>  
    );
}
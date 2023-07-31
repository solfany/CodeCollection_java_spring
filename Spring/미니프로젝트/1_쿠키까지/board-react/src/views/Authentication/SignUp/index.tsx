import React, { useState } from "react";
import axios from 'axios';
import { Button, TextField } from "@mui/material";
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
/*eslint-disable*/
export default function SignUp() {
    const [userEmail, setUserEmail] = useState<string>(''); 
    console.log(userEmail); // userEmail 변수를 사용하는 코드   
    const [userPassword,setUserPassword] = useState<string>('');
    const [userPasswordCheck,setUserPasswordCheck] = useState<string>('');
    const [userNickname,setUserNickname] = useState<string>('');
    const [userPhoneNumber,setUserPhoneNumber] = useState<string>('');
    const [userAddress,setUserAddress] = useState<string>('');
    const [userAddressDetail,setUserAddressDetail] = useState<string>('');
    

    const signUpHandler = () => {
        const data = {
            userEmail,
            userPassword,
            userPasswordCheck,
            userNickname,
            userPhoneNumber,
            userAddress,
            userAddressDetail,
        };
        console.log("Data to be sent:", data); // 이 줄을 추가하세요.
        axios.post("http://localhost:8284/api/auth/signUp", data)
            .then(response => {           
            })
            .catch(error => {            
            });
    };

    return (
        <Card sx={{ minWidth: 275,maxWidth: "50vw" }}>
            <CardContent>
            <Box>
                <TextField fullWidth label="이메일주소" type="email" variant="standard" onChange={(e) => setUserEmail(e.target.value)}/>
                <TextField fullWidth label="비밀번호" type="password" variant="standard" onChange={(e) => setUserPassword(e.target.value)}/>
                <TextField fullWidth label="비밀번호확인" type="password" variant="standard" onChange={(e) => setUserPasswordCheck(e.target.value)}/>
                <TextField fullWidth label="닉네임" variant="standard" onChange={(e) => setUserNickname(e.target.value)}/>       
                <TextField fullWidth label="휴대폰번호" variant="standard" onChange={(e) => setUserPhoneNumber(e.target.value)}/>
                <TextField fullWidth label="주소" variant="standard" onChange={(e) => setUserAddress(e.target.value)}/>
                <TextField fullWidth label="상세주소" variant="standard" onChange={(e) => setUserAddressDetail(e.target.value)}/>
            </Box>
            </CardContent>
            <CardActions>
                <Button fullWidth onClick={() => signUpHandler()} variant="text">회원가입</Button>
                
            </CardActions>
        </Card>     
        
    )
}

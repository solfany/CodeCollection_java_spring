import './App.css';
import Authentication from './views/Authentication';


import MainLayout from './views/layouts/MainLayout';
/*eslint-disable*/

export default function App() {
  // const [connection,setConnection] = useState<string>('');

  // const connectionTest =() => {
  //   axios.get('http://localhost:4000/').then((response) => {
  //     setConnection(response.data);
  //   }).catch((error) => {
  //     setConnection(error.message);
  //   })
  // }

  // useEffect(() => {
  //   connectionTest();
  // },[]);

  return (
    <>
      <MainLayout />
      {/* <Authentication /> */}
    </>
  );
}



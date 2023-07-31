import logo from './logo.svg'; 
import './App.css';
import { useEffect, useState } from "react";
import axios from "axios";

export default function App() {
  const [connection,setConnection] = useState<string>('');

  const connectionTest =() => {
    axios.get('http://localhost:8284/').then((response) => {
      setConnection(response.data);
    }).catch((error) => {
      setConnection(error.message);
    })
  }

  useEffect(() => {
    connectionTest();
  },[]);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo"/>
        <p>{connection}</p>
      </header>
    </div>
  );
}

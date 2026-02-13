import { useState } from 'react'
import './App.css'
import HelloDiv from './HelloDiv'
import Navbar from './components/Navbar';

function App() {
  let value : boolean = true;
  return (
    <>
      <Navbar></Navbar>
      <HelloDiv isLogin={value}></HelloDiv>
      <HelloDiv isLogin={false}></HelloDiv>
    </>

  )
}

export default App

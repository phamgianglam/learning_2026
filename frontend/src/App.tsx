import './App.css'
import Navbar from './components/Navbar';
import ItemDetailedPage from './components/ItemDetailedPage';

function App() {
  let value : boolean = true;
  return (
    <>
      <Navbar></Navbar>
      <div></div>
      <div className='bg-white w-250 mx-auto'>
        <ItemDetailedPage></ItemDetailedPage>
      </div>
      <div></div>
    </>
  )
}

export default App

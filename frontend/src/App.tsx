import './App.css'
import Navbar from './components/Navbar';
import ItemDetailedPage from './components/ItemDetailedPage';
import ItemListPage from './components/ItemListPage';
import { mockItems } from './data/mockItems';

function App() {
  let value: boolean = true;
  return (
    <>
      <Navbar></Navbar>
      <div></div>
      <div className='bg-white w-250 mx-auto'>
        {/* <ItemDetailedPage></ItemDetailedPage> */}
        <ItemListPage items={mockItems}></ItemListPage>
      </div>
      <div></div>
    </>
  )
}

export default App

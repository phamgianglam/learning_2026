import './App.css'
import Navbar from './components/Navbar'
import ItemDetailedPage from './components/ItemDetailedPage'
import ItemListPage from './components/ItemListPage'
import NotFound from './components/NotFound'
import { Routes, Route } from 'react-router'

function App() {
  return (
    <>
      <Navbar />
      <div></div>
      <div className="bg-white w-250 mx-auto">
        <Routes>
          <Route path="/" element={<ItemListPage />} />
          <Route path="/item/:id" element={<ItemDetailedPage />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
      <div></div>
    </>
  )
}

export default App

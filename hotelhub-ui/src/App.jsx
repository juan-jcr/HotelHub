import React from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import HomePage from './components/home/HomePage';
import Navbar from './components/common/Navbar';
import FooterComponent from './components/common/Footer';

import './App.css'

function App() {




  return (
    <BrowserRouter>
      <div className="App">
        <Navbar/>
        <div className="content">
          <Routes>
            {/* Public Routes */}
            <Route exact path="/home" element={<HomePage />} />
          </Routes>
        </div>
        <FooterComponent/>
      </div>
    </BrowserRouter>

  )
}

export default App

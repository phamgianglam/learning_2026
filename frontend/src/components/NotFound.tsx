import React from 'react'
import { Link } from 'react-router'

export default function NotFound() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
      <div className="text-center p-6">
        <h1 className="text-6xl font-bold text-gray-800">404</h1>
        <p className="mt-4 text-xl text-gray-600">Page not found.</p>
        <Link to={`/`}>
            <div className='text-l'>Return to home</div>
        </Link>
      </div>
    </div>
  )
}

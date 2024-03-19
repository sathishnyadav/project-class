import React from 'react'
import { Routes,Route } from 'react-router-dom'
import UpdateUser from './UpdateUser'

function UserHomePage() {
    return (
        <div className="userhompage">
        <Routes>
            <Route path="/updateuser" element={<UpdateUser/>}/>
        </Routes>
        </div>
    )
}

export default UserHomePage

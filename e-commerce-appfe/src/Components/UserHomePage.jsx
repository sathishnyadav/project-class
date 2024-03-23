import React from 'react'
import { Routes,Route } from 'react-router-dom'
import UpdateUser from './UpdateUser'
import UserProduct from './UserProduct'
import UserNavbar from './UserNavbar'
import UserLocation from './UserLocation'

function UserHomePage() {
    return (
        <div className="userhompage">
            <UserNavbar/>
        <Routes>
            <Route path='/' element={<UserProduct/>}/>
            <Route path="/updateuser" element={<UpdateUser/>}/>
            <Route path='/addAdress' element={<UserLocation/>}/>
        </Routes>
        </div>
    )
}
export default UserHomePage

import React from 'react'
import "../styles/UserSignUp.css"
import axios from 'axios'
import { useState } from 'react'
function UserSignUp() {
    let [name,setname] = useState("")
    let [email,setemail] = useState("")
    let [gst_number,setgst] = useState("")
    let [phone,setphone] = useState("")
    let [password,setpassword] = useState("")

    let data = {name,email,gst_number,phone,password}
    
    let adduser = (e) =>{
        e.preventDefault();
        axios.post('http://localhost:8080/user',data)
        .then((res)=>{
            console.log(res);
            alert("Data Added succesfull")
        })
        .catch((err)=>{
            console.log(err);
            alert("Data Not Found")
        })
    }
    return (
        <div className="usersignup">
        <form onSubmit={adduser} action="">
                <label htmlFor="">Name</label>
                <input required value={name} onChange={(e)=>{setname(e.target.value)}} type="text" placeholder="Enter the Name"  />
                <label htmlFor="">GST_number</label>
                <input required type="text" value={gst_number} onChange={(e)=>{setgst(e.target.value)}}  placeholder="Enter the GST "  />
                <label htmlFor="">Email</label>
                <input required type="email"  value={email} onChange={(e)=>{setemail(e.target.value)}} placeholder="Enter the Email"  />
                <label htmlFor="">Phone No</label>
                <input required type="tel" value={phone} onChange={(e)=>{setphone(e.target.value)}}  pattern="[0-9]{10}" placeholder="Enter the Phone"  />
                <label htmlFor="">Password</label>
                <input required type="password" value={password} onChange={(e)=>{setpassword(e.target.value)}} placeholder="Enter the Password"  />
            <button className='btn btn-outline-info'>Submit</button>
            </form>
        </div>
    )
}
export default UserSignUp
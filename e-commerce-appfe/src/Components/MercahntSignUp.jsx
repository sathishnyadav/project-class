import { useState } from 'react'
import '../styles/MerchantSignup.css'
function MercahntSignUp() {
    let [name,setname] = useState("")
    let [email,setemail] = useState("")
    let [gst_number,setgst] = useState("")
    let [phone,setphone] = useState("")
    let [password,setpassword] = useState("")
    return (
        <div className="merchantsignup">
            <form action="">
                <label htmlFor="">Name</label>
                <input value={name} onChange={(e)=>{setname(e.target.value)}} type="text" placeholder="Enter the Name" required />
                <label htmlFor="">GST_number</label>
                <input type="text" value={gst_number} onChange={(e)=>{setgst(e.target.value)}}  placeholder="Enter the GST " required />
                <label htmlFor="">Email</label>
                <input type="email"  value={email} onChange={(e)=>{setemail(e.target.value)}} placeholder="Enter the Email" required />
                <label htmlFor="">Phone No</label>
                <input type="tel" value={phone} onChange={(e)=>{setphone(e.target.value)}}  pattern="[0-9]{10}" placeholder="Enter the Phone" required />
                <label htmlFor="">Password</label>
                <input type="password" value={password} onChange={(e)=>{setpassword(e.target.value)}} placeholder="Enter the Password" required />
            <button className='btn btn-outline-info'>Submit</button>
            </form>
        </div>
    )
}
export default MercahntSignUp

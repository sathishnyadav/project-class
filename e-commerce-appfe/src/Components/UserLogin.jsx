import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import axios from 'axios';
import { useNavigate,Link } from 'react-router-dom';
const UserLogin = () => {
    let [email,setemail] = useState("")
    let [password,setpassword] = useState("")

    let navigate = useNavigate()
    
    function verifyMerchant (e) {
        e.preventDefault();
        axios.post(`http://localhost:8080/users/verify-by-email?email=${email}&password=${password}`)
        .then((res)=>{
            console.log(res.data.body);
            localStorage.setItem("User",JSON.stringify(res.data.body))
            navigate('/userhomepage');
            alert("Login Successfull");
        })
        .catch((err)=>{
            console.log(err.data);
            alert("Invalid Credentials")
        })
    }
    return ( 
        <div className="UserLogin">
             <Form onSubmit={verifyMerchant}>
                <Form.Group className="mb-3" controlId="formGroupEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control value={email} onChange={(e)=>{setemail(e.target.value)}} type="email" placeholder="Enter email" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formGroupPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control value={password} onChange={(e)=>{setpassword(e.target.value)}} type="password" placeholder="Password" />
                </Form.Group>
                <Form.Group>
                    <button className='btn btn-success mx-5'>Sign In</button>
                    <button className='btn btn-danger mx-5'><Link to="/usersignup">Sign Up</Link></button>
                </Form.Group>
            </Form>
        </div>
     );
}
 
export default UserLogin;
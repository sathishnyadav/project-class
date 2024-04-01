import React, { useState } from 'react'
import { useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import '../styles/ReadContnt.css'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import FavoriteIcon from '@mui/icons-material/Favorite';
 function ReadContent() {

  let user = JSON.parse(localStorage.getItem("User"))
  let param = useParams()
  let [data,setdata ] = useState("")
  
    useEffect(() => {
        axios.get(`http://localhost:8080/products/find-by-id/${param.id}`)
          .then((res) => {
            console.log(res.data.body);
            setdata(res.data.body)
          })
          .catch((err) => {
            console.log(err);
          })
      }, [])


      function addtoCart(e){
          e.preventDefault();
          axios.put(`http://localhost:8080/products/${user.id}/${param.id}`,data)
          .then((res)=>{
              console.log(res);
              toast.success("Product Added succesfull")
          })
          .catch((err)=>{
              console.log(err);
              toast.error("Something Went Wrong")
          })
      }
      
      function addwishList(e){
        e.preventDefault();
        axios.put(`http://localhost:8080/products/wishlist/${user.id}/${param.id}`,data)
        .then((res)=>{
            console.log(res);
            toast.success("Product Added succesfull")
        })
        .catch((err)=>{
            console.log(err);
            toast.error("Something Went Wrong")
        })
      }
  return (
    <div className='read_disp'>
      <FavoriteIcon id="icon" onClick={addwishList}/>
        <div className="image">
          <img src={data.image_url} alt="" />
        </div>
        
        <div className="details">
          <br />
          <h3>{data.name}</h3> 
          <h2>{data.brand}</h2>
          <strike>M.R.P : {data.cost}</strike><br /><br />
          
          <sup><sup>₹</sup></sup><span id="amount">{data.cost * .88}</span>
          <br /><br />
          <p>{data.description}</p>
          <button id="cart" onClick={addtoCart}  className='btn btn-warning'>Add to Cart</button>
         
        <ToastContainer/>
        </div>
    </div>
  )
}

export default ReadContent

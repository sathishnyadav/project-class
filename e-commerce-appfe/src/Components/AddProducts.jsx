import React, { useState } from 'react'
import '../styles/AddProducts.css'
import axios from 'axios'
function AddProducts() {
    let [name,setname] = useState("")
    let [brand,setbrand] = useState("")
    let [category,setcategory] = useState("")
    let [description,setcdecription] = useState("")
    let [cost,setcost] = useState("")
    let [image_url,setuimageurl] = useState("")

    let data = {name,brand,category,description,cost,image_url}
    let admin = JSON.parse(localStorage.getItem("Merchant"))
    let addData = (e) =>{
        e.preventDefault();
        axios.post(`http://localhost:8080/products/${admin.id}`,data)
        .then((res)=>{
            console.log(res);
            alert("Product Added succesfull")
        })
        .catch((err)=>{
            console.log(err);
            alert("Something Went Wrong")
        })
    }
  return (
    <div className='AddProducts'>
        <form onSubmit={addData} action="">
            <label htmlFor="">Name:</label>
            <input value={name} onChange={(e)=>{setname(e.target.value)}} type="text" required placeholder='Enter the name' />
            <label htmlFor="">Brand:</label>
            <input value={brand} onChange={(e)=>{setbrand(e.target.value)}} type="text" required placeholder='Enter the brand' />
            <label htmlFor="">Category:</label>
            <input value={category} onChange={(e)=>{setcategory(e.target.value)}} type="text" required placeholder='Enter the category' />
            <label htmlFor="">Description:</label>
            <input value={description} onChange={(e)=>{setcdecription(e.target.value)}} type="text" required placeholder='Enter the propertiy' />
            <label htmlFor="">Cost:</label>
            <input value={cost} onChange={(e)=>{setcost(e.target.value)}} type="text" required placeholder='Enter the cost ' />
            <label htmlFor="">Image URL:</label>
            <input value={image_url} onChange={(e)=>{setuimageurl(e.target.value)}} type="text" required placeholder='Enter the image url' />
            <button className='btn btn-success'>Submit</button>
        </form>
    </div>
  )
}
export default AddProducts

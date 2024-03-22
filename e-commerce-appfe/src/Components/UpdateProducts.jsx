import React, { useEffect, useState } from 'react'
import '../styles/UpdateProducts.css'
import axios from 'axios'
import { useParams } from 'react-router-dom'
function UpdateProducts() {
    let [name,setname] = useState("")
    let [brand,setbrand] = useState("")
    let [category,setcategory] = useState("")
    let [description,setcdecription] = useState("")
    let [cost,setcost] = useState("")
    let [image_url,setuimageurl] = useState("")
    let [id,setid] = useState("")

    let param = useParams()
    
    useEffect(()=>{
        axios.get(`http://localhost:8080/products/find-by-id/${param.id}`)
        .then((res)=>{
            console.log(res.data.body);
            setname(res.data.body.name)
            setbrand(res.data.body.brand)
            setcategory(res.data.body.category)
            setcdecription(res.data.body.description)
            setcost(res.data.body.cost)
            setuimageurl(res.data.body.image_url)
            setid(res.data.body.id)
        })
        .catch((err)=>{
            console.log(err);
        })
    },[])


    let data = {name,brand,category,description,cost,image_url,id}
    
    let editData = (e) =>{
        e.preventDefault();
        axios.put(`http://localhost:8080/products`,data)
        .then((res)=>{
            console.log(res);
            alert("Product Edited succesfull")
        })
        .catch((err)=>{
            console.log(err);
            alert("Something Went Wrong")
        })
    }
  return (
    <div className='UpdateProducts'>
        <form onSubmit={editData} action="">
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
            <button className='btn btn-info'>Submit</button>
        </form>
    </div>
  )
}
export default UpdateProducts

import React, { useEffect, useState } from 'react'
import axios from 'axios'
 function ProductView() {
  let [item,setitem] = useState([])
  let admin = JSON.parse(localStorage.getItem("Merchant"))

  useEffect(()=>{
    axios.get(`http://localhost:8080/products/${admin.id}`)
    .then((res)=>{
        console.log(res.data.body);
        setitem(res.data.body)
    })
    .catch((err)=>{
        console.log(err);
    })
  },[])
  return (
    <div>
      {item.map((x)=>{
        return(
          <>
            <h1>{x.name}</h1>
          </>
        )
      })}
    </div>
  )
}
export default  ProductView
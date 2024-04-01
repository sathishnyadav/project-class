import React, { useState ,useEffect} from 'react'
import axios from 'axios'
export default function UserCart() {
    let [data,setdata] = useState([])
    useEffect(() => {
        axios.get(`http://localhost:8080/products`)
          .then((res) => {
            console.log(res.data.body);
            setdata(res.data.body)
          })
          .catch((err) => {
            console.log(err);
          })
      }, [])
  return (
    <div>UserCart</div>
  )
}

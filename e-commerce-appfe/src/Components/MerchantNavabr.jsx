import React from 'react'
import { Link } from 'react-router-dom'
import '../styles/Merchantnavbar.css'
import Dropdown from 'react-bootstrap/Dropdown';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
function MerchantNavabr() {
  return (
    <nav id='navbar'>
        <div className="logo">
            <h1><span>Shoppers</span>Cart</h1>
        </div>
        <div className="option">
            <Link to="/merchanthomepage/productview">View Products</Link>
        </div>


        <div className="account">
        <Dropdown>
      <Dropdown.Toggle variant="outline-dark" id="dropdown-basic">
      <AccountCircleIcon/> Account
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item href="/merchanthomepage/updatemerchant">Edit Account</Dropdown.Item>
        <Dropdown.Item href="/">Logout</Dropdown.Item>
      </Dropdown.Menu>
    </Dropdown>
        </div>
    </nav>
  )
}
export default MerchantNavabr
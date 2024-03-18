import { Route, Routes } from "react-router-dom";
import MerchantNavabr from "./MerchantNavabr";
import ProductView from "./ProductView";
import UpdateMerchant from "./UpdateMerchant";
import AddProducts from "./AddProducts";

const MechantHomePage = () => {
    return (
        <div className="mhp">
            <MerchantNavabr/>
           <Routes>
            <Route path="/productview" element={<ProductView/>}/>
            <Route path="/updatemerchant" element={<UpdateMerchant/>}/>
            <Route path="/addproduts" element={<AddProducts/>}/>
           </Routes>
        </div>
      );
}
 
export default MechantHomePage;
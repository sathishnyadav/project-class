import { Route, Routes } from "react-router-dom";
import MerchantNavabr from "./MerchantNavabr";
import ProductView from "./ProductView";
import UpdateMerchant from "./UpdateMerchant";

const MechantHomePage = () => {
    return (
        <div className="mhp">
            <MerchantNavabr/>
           <Routes>
            <Route path="/productview" element={<ProductView/>}/>
            <Route path="/updatemerchant" element={<UpdateMerchant/>}/>
           </Routes>
        </div>
      );
}
 
export default MechantHomePage;
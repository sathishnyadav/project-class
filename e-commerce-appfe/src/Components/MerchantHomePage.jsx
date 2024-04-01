import { Route, Routes } from "react-router-dom";
import MerchantNavabr from "./MerchantNavabr";
import ProductView from "./ProductView";
import UpdateMerchant from "./UpdateMerchant";
import AddProducts from "./AddProducts";
import UpdateProducts from "./UpdateProducts";
import ReadContent from "./ReadContent";

const MechantHomePage = () => {
    return (
        <div className="mhp">
            <MerchantNavabr/>
           <Routes>
            <Route path="/productview" element={<ProductView/>}/>
            <Route path="/updatemerchant" element={<UpdateMerchant/>}/>
            <Route path="/addproduts" element={<AddProducts/>}/>
            <Route path="/updateproduct/:id" element={<UpdateProducts/>}/>
            <Route path='readData/:id' element={<ReadContent/>}/>
           </Routes>
        </div>
      );
}
 
export default MechantHomePage;
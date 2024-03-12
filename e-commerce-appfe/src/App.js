import LandingPage from "./Components/LandingPage";
import { BrowserRouter,Routes,Route } from "react-router-dom";
import MerchantLogin from "./Components/MerchantLogin";
import UserLogin from "./Components/UserLogin";
import 'bootstrap/dist/css/bootstrap.min.css';
import MercahntSignUp from "./Components/MercahntSignUp";
import MechantHomePage from "./Components/MerchantHomePage";
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<LandingPage/>}/>
        <Route path="/merchant" element={<MerchantLogin/>}/>
        <Route path="/user" element={<UserLogin/>}/>
        <Route path="/merchantsignup" element={<MercahntSignUp/>}/>
        <Route path="/merchanthomepage" element={<MechantHomePage/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}
export default App;

import LandingPage from "./Components/LandingPage";
import { BrowserRouter,Routes,Route } from "react-router-dom";
import MerchantLogin from "./Components/MerchantLogin";
import UserLogin from "./Components/UserLogin";
import 'bootstrap/dist/css/bootstrap.min.css';
import MercahntSignUp from "./Components/MercahntSignUp";
import MechantHomePage from "./Components/MerchantHomePage";
import Error from "./Components/Error";
import Protect from "./Components/Protect";
import UserSignUp from "./Components/UserSignUp";
import UserHomePage from "./Components/UserHomePage";
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<LandingPage/>}/>
        <Route path="/*" element={<Error/>}/>
        <Route path="/merchant" element={<MerchantLogin/>}/>
        <Route path="/user" element={<UserLogin/>}/>
        <Route path="/merchantsignup" element={<MercahntSignUp/>}/>
        <Route path="/merchanthomepage/*" element={<Protect Child={MechantHomePage}/>}/>
        <Route path="/usersignup" element={<UserSignUp/>}/>
        <Route path="/userhomepage/*" element={<UserHomePage/>}/>

      </Routes>
      </BrowserRouter>
    </div>
  );
}
export default App;

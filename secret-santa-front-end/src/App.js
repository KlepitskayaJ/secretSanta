import {Route, Routes} from "react-router-dom";
import LoginPage from "./pages/login-registration-pages/loginPage";
import RegistrationPage from "./pages/login-registration-pages/registrationPage";

function App() {
  return (
      <>
        <Routes>
            <Route path='/login' element={<LoginPage/>}/>
            <Route path='/register' element={<RegistrationPage/>}/>
        </Routes>
      </>
  );
}

export default App;

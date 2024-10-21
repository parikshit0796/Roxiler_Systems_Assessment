import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Stylesheets/Navbar.css"

const Navbar = () => {

    const navigate = useNavigate();

    const handleLogOut = () => {
        localStorage.removeItem("loggedInUserRole");
        localStorage.removeItem("loggedInUserId");
        localStorage.clear();
        navigate("/");
      };
      
      const handleProfile = () => {
        const role = JSON.parse(localStorage.getItem("loggedInUserRole"));
        console.log("Role : "+role);
        if (role === "ROLE_SYSTEM_ADMIN") {
          console.log(role);
          navigate("/admin-home");
          
        } else if (role === "ROLE_STORE_OWNER") {
          console.log(role);
          navigate("/owner-home");
        } else {
          console.log(role);
          navigate("/user-home");
        }
      };
      
      const handleChangePassword = () => {
        navigate("/change-password");
      };
      

  return (
    <>
      <div>
        <div className="nav_bar d-flex p-lg-3 col-lg-7 justify-content-center">
            <button className="btn mx-5" onClick={handleProfile}>
              <strong>Home</strong>
            </button>
            <button className="btn mx-5" onClick={handleChangePassword} >
              <strong>Change Password</strong>
            </button>
            <button className="btn mx-5" onClick={handleLogOut}>
              <strong>Logout</strong>
            </button>
        </div>
      </div>
    </>
  );
};

export default Navbar;

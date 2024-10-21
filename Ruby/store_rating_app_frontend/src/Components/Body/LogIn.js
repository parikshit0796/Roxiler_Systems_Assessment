import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AxiosConfig from "../../AxiosConfig";
import "./Stylesheets/LogIn.css";

const LogIn = () => {
  const [invalidEmail, setInvalidEmail] = useState(false);
  const [invalidPass, setInvalidPass] = useState(false);
  const [loginUserData, setLoginUserData] = useState({
    email: "",
    password: "",
  });
  const navigate = useNavigate();

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setLoginUserData({
      ...loginUserData,
      [name]: value,
    });
  };
  const validateEmail = (e) => {
    if (
      /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(e.target.value)
    ) {
      setInvalidEmail(false);
      changeHandler(e);
    } else {
      setInvalidEmail(true);
    }
  };

  const showPassword = (id) => {
    var x = document.getElementById(id);
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  };

  const validatePassword = (e) => {
    if (
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/.test(
        e.target.value
      )
    ) {
      setInvalidPass(false);
      changeHandler(e);
    } else {
      setInvalidPass(true);
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    if (invalidEmail) {
      alert("Invalid email!!!");
    } else if (invalidPass) {
      alert("Invalid Password!!!");
    } else {
      try {
        const response = await AxiosConfig.post("/user/signin", loginUserData);
        const data = response.data;
        localStorage.setItem("token", response.data.token);

        if (response.status === 200) {
          localStorage.setItem("loggedInUserRole", JSON.stringify(data.role));
          localStorage.setItem("loggedInUserId", JSON.stringify(data.id));
          
          if (data.role === "ROLE_STORE_OWNER") {
            console.log(data);
            navigate("/store-owner-home");
          } else if (data.role === "ROLE_SYSTEM_ADMIN") {
            console.log(data);
            navigate("/admin-home");
          } else {
            navigate("/user-home")
          }
        }
      } catch (error) {
        alert("Please fill valid details!!!");
        console.log(error);
        window.location.reload();
      }
    }
  };
  return (
    <>
      <div className="page-container" style={{  marginTop: 100, marginLeft:200 , marginRight:200 }}>
        <div className="heading">
          <h1>LOG IN</h1>
        </div>
    
        <div className="">
          <label className="form-label" htmlFor="email">
            Email
          </label>
          <input
            className="form-control"
            type="email"
            id="email"
            name="email"
            onChange={(e) => validateEmail(e)}
          />
          <div>
            {invalidEmail && (
              <>
                <div>Invalid Email format!!!</div>
              </>
            )}
          </div>
        </div>
        <br></br>
        <div className="">
          <label className="form-label" htmlFor="password">
            Password
          </label>
          <input
            type="password"
            id="password"
            className="form-control"
            name="password"
            onChange={(e) => validatePassword(e)}
          />
        </div>
        <div>
          <input
            className="m-1"
            type="checkbox"
            name=""
            id=""
            onClick={() => showPassword("password")}
          />
          <span>Show</span>
        </div>
            <br></br>
        <div style={{ marginLeft: "42%" }}>
          <button
            autoFocus
            className="cstmBtn"
            type="sumit"
            onClick={handleLogin}
          >
            LogIn
          </button>
          
          <div>
          <br></br>
            <div><strong>Don't have an account?</strong></div>
            <Link to="/registration">
              <button className="cstmBtn">
                Register
              </button>
            </Link>
          </div>
        </div>
      </div>
    </>
  );
};

export default LogIn;

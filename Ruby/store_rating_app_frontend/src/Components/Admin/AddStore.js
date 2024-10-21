import React, { useEffect, useState } from "react";
import Navbar from "../Body/Navbar";
import { Link, useNavigate } from "react-router-dom";
import AxiosConfig from "../../AxiosConfig";

const AddStore = () => {
  const [registerUserData, setRegisterUserData] = useState({
    storeName: "",
    email: "",
    storeAddress: "",
    storeOwnerId: "",
  });

  const [invalidStoreName, setInvalidStoreName] = useState(false);
  const [invalidEmail, setInvalidEmail] = useState(false);
  const [invalidAddress, setInvalidAddress] = useState(false);
  const [invalidStoreOwner, setInvalidStoreOwner] = useState(false);
  const [ownerArray, setOwnerArray] = useState([]);
  const [owner, setOwner] = useState("");

  const navigate = useNavigate();

  const validateStoreName = (e) => {
    if (/^[a-zA-Z ]{3,30}$/.test(e.target.value)) {
      setInvalidStoreName(false);
      changeHandler(e);
    } else {
      setInvalidStoreName(true);
    }
  };

  const validateOwnerName = (value) => {
    if (value) {
      setInvalidStoreOwner(false);
      setOwner(value);
  
      setRegisterUserData((prevData) => ({
        ...prevData,
        storeOwnerId: value, 
      }));
    } else {
      setInvalidStoreOwner(true);
    }
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

  const validateAddress = (e) => {
    if (/^.{5,}$/.test(e.target.value)) {
      setInvalidAddress(false);
      changeHandler(e);
    } else {
      setInvalidAddress(true);
    }
  };

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setRegisterUserData({
      ...registerUserData,
      [name]: value,
    });
  };
  const submitRegister = async (event) => {
    event.preventDefault();
    if (
      invalidStoreName ||
      invalidStoreOwner ||
      invalidEmail ||
      invalidAddress
    ) {
      alert("invalid data filled!!!");
      // window.location.reload();
    } else {
      const storeOwnerId = owner;

      const dataToSend = { ...registerUserData, storeOwnerId: owner };

      console.log("Payload to be sent: ", dataToSend);
      await AxiosConfig.post("/store/addstore", dataToSend)
        .then((response) => {
          alert("New User Added Successful");
          console.log(response.data);
          navigate("/admin-home");
        })
        .catch((error) => {
          alert("provide valid details!!!");
        });
    }
  };

  useEffect(() => {
    const getOwners = async () => {
      try {
        const response = await AxiosConfig.get("/user/getallstoreowners");
        console.log(response.data);
        setOwnerArray(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    getOwners();
  }, []);

  return (
    <>
      <div className="page-container">
        <Navbar />
        <br></br> <br></br>
        <div className="heading">
          <h1>Add Store</h1>
        </div>
        <form>
          <div className="mb-3">
            <label htmlFor="storename" className="form-label">
              Store Name
            </label>
            <input
              type="text"
              name="storeName"
              className="form-control"
              id="storename"
              required={true}
              onChange={(e) => validateStoreName(e)}
            />
          </div>

          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              Email address
            </label>
            <input
              type="email"
              name="email"
              className="form-control"
              id="email"
              placeholder="Please Enter Valid Email ID"
              aria-describedby="emailHelp"
              required={true}
              onChange={(e) => (e ? validateEmail(e) : "")}
            />

            <div>
              {invalidEmail && (
                <>
                  <div>Invalid Email format!!!</div>
                </>
              )}
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="address" className="form-label">
              address
            </label>
            <textarea
              className="form-control"
              id="address"
              rows="3"
              name="storeAddress"
              onChange={(e) => (e ? validateAddress(e) : "")}
            ></textarea>
            <div>
              {invalidAddress && (
                <>
                  <div>Minimum 5 charaters required!!!</div>
                </>
              )}
            </div>
          </div>

          <div className="">
            <label htmlFor="storeowner" className="form-label">
              Store Owner Name
            </label>
            <select
              name="storeOwnerId"
              className="form-select"
              id="storeowner"
              required={true}
              onChange={(e) => validateOwnerName(e.target.value)}
            >
              <option selected disabled >
                -- Select Owner --
              </option>
              {ownerArray &&
                ownerArray.map((item) => (
                  <option key={item.id} value={item.id}>
                    {item.name}
                  </option>
                ))}
            </select>
            <div>
              {invalidStoreOwner && (
                <>
                  <div>Invalid Owner!!!</div>
                </>
              )}
            </div>
          </div>
          <br></br>

          <div style={{ marginLeft: "42%" }}>
            <button
              autoFocus
              className="cstmBtn"
              type="submit"
              onClick={submitRegister}
            >
              Add User
            </button>
          </div>
        </form>
      </div>
    </>
  );
};

export default AddStore;

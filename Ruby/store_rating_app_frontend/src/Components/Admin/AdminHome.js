import React, { useEffect, useState } from 'react';
import Navbar from "../Body/Navbar";
import { Link, useNavigate } from "react-router-dom";
import AxiosConfig from '../../AxiosConfig';
import "./Stylesheets/AdminHome.css";


const AdminHome = () => {
  const [userList, setUserList] = useState([]);

  useEffect(() => {
    const getAllUsers = async () => {
      try {
        const response = await AxiosConfig.get("user/getallusers");
        console.log(response)
        setUserList(response.data);
      } catch (error) {
        console.log(error)
      }
    }
    getAllUsers();
  }, []);

  return (
    <>
      <div className="page-container">
        <Navbar />
        <br></br> <br></br>
        <div className="heading">
          <h1>Admin Home </h1>
        </div>
        <div>
          <div>
            <Link to="/add-user">
            <button className="btn btn-light" >Add User</button>
            </Link>
            <Link to="/add-store">
            <button className="btn btn-light" >Add Store</button>
            </Link>
            <Link to="/stores-list">
            <button className="btn btn-light" >Store List</button>
            </Link>
          </div>
        </div>
        <div>
          <table className="table table-head">
            <thead>
              <tr>
                <th> Id </th>
                <th> Name </th>
                <th> Role </th>
                <th> Email </th>
                <th> Address </th>
                <th> Rating </th>
              </tr>
            </thead>
            <tbody>
              {userList &&
                userList.map((data) => (
                  <tr key={data.id}>
                    <td>{data.id} </td>
                    <td>{data.name} </td>
                    <td>{data.role}</td>
                    <td>{data.email} </td>
                    <td>{data.address} </td>
                    <td>{data.rating} </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default AdminHome;

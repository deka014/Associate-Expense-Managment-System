import React, { useState, useEffect } from 'react'
import authService from '../services/authService'

const Menu = () => {
  const user = authService.getCurrentUser()

  return (
    <div>
      <nav class='navbar navbar-expand-sm navbar-dark bg-dark p-3 '>
        <div class='container-fluid'>
          <a class='navbar-brand' href='/'>
            <img
              height={48}
              src='https://1000logos.net/wp-content/uploads/2016/10/Batman-Logo-1966-768x432.png'
            />
          </a>
          <button
            class='navbar-toggler'
            type='button'
            data-bs-toggle='collapse'
            data-bs-target='#mynavbar'
          >
            <span class='navbar-toggler-icon'></span>
          </button>
          <div class='collapse navbar-collapse' id='mynavbar'>
            <ul class='navbar-nav'>
              {!user || !user.token ? (
                <li class='nav-item mx-3 fs-5'>
                  <a class='nav-link' href='/login'>
                    Login
                  </a>
                </li>
              ) : (
                <>
                  <li class='nav-item mx-3 fs-5'>
                    <a
                      class='nav-link'
                      href='/'
                      onClick={() => {
                        authService.logout()
                      }}
                    >
                      Logout {user.name}
                    </a>
                  </li>
                  <li class='nav-item  mx-3 fs-5'>
                    <a class='nav-link' href={`/employee-dashboard/${user.id}`}>
                      Employee Dashboard
                    </a>
                  </li>
                  <li class='nav-item  mx-3 fs-5'>
                    <a class='nav-link' href={`/manager-dashboard/${user.id}`}>
                      Manager Dashboard
                    </a>
                  </li>
                  <li class='nav-item  mx-3 fs-5'>
                    <a class='nav-link' href={`/expense/${user.id}`}>
                      View All Expense
                    </a>
                  </li>
                </>
              )}
              
            </ul>
          </div>
        </div>
      </nav>
    </div>
  )
}

export default Menu

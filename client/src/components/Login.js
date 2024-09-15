import React, { useEffect } from 'react'
import { useState } from 'react'
import authService from '../services/authService'
import { toastError } from '../utils/notification'

const Login = () => {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [user, setUser] = useState(authService.getCurrentUser())

  const handleLoginError = (error) => {
    let errorMessage = 'Network Down'
    if (error?.response?.status) {
      switch (error.response.status) {
        case 403:
          errorMessage = 'Wrong Email or Password! 403'
          break
        case 500:
          errorMessage = 'Server error, please try again later. ! 500'
          break
        default:
          errorMessage = `An unexpected error occurred. ${error.response.status}`
      }
    } else {
      errorMessage = 'Network error, please check your connection.'
    }
    toastError(errorMessage)
  }

  useEffect(() => {
    if (user && user.id) {
      window.location.href = `/employee-dashboard/${user.id}`
    }
  }, [])

  const handleOnLogin = async (e) => {
    e.preventDefault()
    try {
      const user = await authService.login(email, password)
      // Handle successful login
      console.log('Login successful:', user)
      window.location.href = `/employee-dashboard/${user.id}`
    } catch (error) {
      // Handle login error
      console.error('Login failed:', error)
      handleLoginError(error)
    }
  }

  return (
    <div className='wrapper bg-dark w-50 mb-5'>
      <h1>Login</h1>
      <form action=''>
        <div className='input-box'>
          <input
            type='email'
            placeholder='Email'
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className='input-box'>
          <input
            type='password'
            placeholder='Password'
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <button class='w-50' type='submit' onClick={handleOnLogin}>
          Submit
        </button>
      </form>
    </div>
  )
}

export default Login

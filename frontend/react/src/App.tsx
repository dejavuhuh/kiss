import { Button, Stack } from '@mui/material'
import { useMutation, useQuery } from '@tanstack/react-query'
import queryClient from './tanstack/query'
import './App.css'

function App() {
  const { data, error } = useQuery({
    queryKey: ['auth', 'me'],
    queryFn: async () => {
      const res = await fetch('http://127.0.0.1:8080/api/auth/me', { credentials: 'include' })
      return await res.json()
    },
  })

  const { mutate: login } = useMutation({
    mutationFn: async () => {
      await fetch('http://127.0.0.1:8080/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: 'a',
          password: 'b',
        }),
        credentials: 'include',
      })
    },
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['auth', 'me'],
      })
    },
  })

  const { mutate: logout } = useMutation({
    mutationFn: async () => {
      await fetch('http://127.0.0.1:8080/api/auth/logout', {
        method: 'POST',
        credentials: 'include',
      })
    },
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['auth', 'me'],
      })
    },
  })

  return (
    <Stack spacing={2}>
      <code>{error ? 'Not login' : JSON.stringify(data, null, 2)}</code>
      <Button variant="contained" onClick={() => login()}>
        Login
      </Button>
      <Button variant="contained" onClick={() => logout()}>
        Logout
      </Button>
    </Stack>
  )
}

export default App

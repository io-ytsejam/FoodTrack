import { userSignIn } from '../actions/userSession';

const verifySession = (history, userSignIn) => {
  const getCookie = (name) =>
    document.cookie.replace(new RegExp('(?:(?:^|.*;\s*)'+ name +
      '\s*\=\s*([^;]*).*$)|^.*$'), '$1');

  const username = localStorage.getItem('username');
  const authToken = localStorage.getItem('authToken');

  userSignIn({ username, authToken });

  if (!authToken) {
    return history.push('/welcome');
  }

  fetch('/api/isLogged', {
    headers: {
      Authorization: 'Bearer ' + authToken
    }
  })
      .then((res) => {
        if (res.ok) {
          res.json().then((isLogged) => {
            if (!isLogged) {
              document.cookie = 'auth-token=;max-age=0';
              history.push('/');
            } else {
              userSignIn(null);
              history.push('/welcome');
            }
          });
        } else throw new Error('User session validation error');
      }).catch((err) => {
        console.error(err.message);
      });
};

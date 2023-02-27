export const createPost = async (jwt, title, content) => {
  try {
    const response = await fetch('/boards', {
      method: 'POST',
      headers: {
        Authorization: jwt,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ title, body: content }),
      credentials: 'include',
    });
    if (!response.ok) {
      throw Error(response.status);
    }
    console.log(response);
  } catch (e) {
    throw Error(e);
  }
};

export const updatePost = async (jwt, title, content, boardID) => {
  try {
    const response = await fetch(`/boards/${boardID}`, {
      method: 'PATCH',
      headers: {
        Authorization: jwt,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ title, body: content }),
      credentials: 'include',
    });
    if (!response.ok) {
      throw Error(response.status);
    }
  } catch (e) {
    throw Error(e);
  }
};

export const getPost = async (jwt, boardID) => {
  console.log(jwt, boardID);
  if (!jwt) {
    return { title: '', body: '' };
  }
  try {
    const response = await fetch(`/boards/${boardID}`, {
      method: 'GET',
      headers: {
        Authorization: jwt,
        'Content-Type': 'application/json',
      },
      credentials: 'include',
    });
    if (!response.ok) {
      throw Error(response.status);
    }
    const { body, title } = await response.json();
    return { body, title };
  } catch (e) {
    throw Error(e);
  }
};

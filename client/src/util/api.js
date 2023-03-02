export const createPost = async (jwt, title, content) => {
  try {
    const response = await fetch('http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards', {
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
   
  } catch (e) {
    throw Error(e);
  }
};

export const updatePost = async (jwt, title, content, boardID) => {
  try {
    const response = await fetch(`http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards/${boardID}`, {
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
  try {
    const response = await fetch(`http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards/${boardID}`, {
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

export const getSearchRsult = async (jwt, q, page) => {
  try {
    page = page ?? 1;
    const response = await fetch(`http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards/search?q=${q}&page=${page}`, {
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
    const data = await response.json();
    return data;
  } catch (e) {
    throw Error(e);
  }
};

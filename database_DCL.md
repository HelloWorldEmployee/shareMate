CREATE USER 'admin' @'%' IDENTIFIED BY 'sideproject';

GRANT ALL PRIVILEGES ON shareMate.\* TO 'admin' @'%';

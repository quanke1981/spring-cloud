INSERT INTO user(id, active, username, password) 
SELECT NULL, 1, 'quank', '123' WHERE NOT EXISTS(SELECT 1 FROM user WHERE username='quank');

INSERT INTO user(id, active, username, password) 
SELECT NULL, 1, 'quanx', '123' WHERE NOT EXISTS(SELECT 1 FROM user WHERE username='quanx');

INSERT INTO role(id, role_name) 
SELECT NULL, 'ADMIN' WHERE NOT EXISTS(SELECT 1 FROM role WHERE role_name='ADMIN');


INSERT INTO role(id, role_name) 
SELECT NULL, 'USER' WHERE NOT EXISTS(SELECT 1 FROM role WHERE role_name='USER');

SELECT @user_id_qk:=id FROM user WHERE username='quank';
SELECT @user_id_qx:=id FROM user WHERE username='quanx';
SELECT @role_admin_id:=id FROM role WHERE role_name='ADMIN';
SELECT @role_user_id:=id FROM role WHERE role_name='USER';

INSERT INTO user_role (user_id, role_id)
SELECT @user_id_qk, @role_admin_id WHERE NOT EXISTS(SELECT 1 FROM user_role WHERE user_id=@user_id_qk AND role_id=@role_admin_id);

INSERT INTO user_role (user_id, role_id)
SELECT @user_id_qk, @role_user_id WHERE NOT EXISTS(SELECT 1 FROM user_role WHERE user_id=@user_id_qk AND role_id=@role_user_id);

INSERT INTO user_role (user_id, role_id)
SELECT @user_id_qx, @role_user_id WHERE NOT EXISTS(SELECT 1 FROM user_role WHERE user_id=@user_id_qx AND role_id=@role_user_id);
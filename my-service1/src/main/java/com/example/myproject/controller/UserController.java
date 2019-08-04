package com.example.myproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.myproject.model.User;
import com.example.myproject.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController extends BaseController<User, UserService> {
	
//	@Autowired
//	private RoleService roleService;
//	
//	
//	@RequestMapping(method=RequestMethod.POST)
//    @ResponseBody
//    @Override
//    public User post(@RequestBody User entity) {
//		
//		Set<Role> roles = entity.getRoles();
//		if(roles!=null) {
//			while(roles.iterator().hasNext()) {
//				Role curRole = roles.iterator().next();
//				Optional<Role> role = roleService.loadRoleByRoleName(curRole.getRoleName());
//		        if(role.isPresent()) {
//		        	curRole.setId(role.get().getId());
//		        }
//			}
//			
//		}
//        return service.save(entity);
//    }
	
}

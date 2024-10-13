package org.example.factories;

import org.example.user.dto.function.RequestToUserFunction;
import org.example.user.dto.function.UserToResponseFunction;
import org.example.user.dto.function.UsersToResponseFunction;

public class DtoFunctionFactory {
    public UsersToResponseFunction usersToResponse(){return new UsersToResponseFunction();}
    public UserToResponseFunction userToResponse(){return new UserToResponseFunction();}
    public RequestToUserFunction requestToUser(){return new RequestToUserFunction();}
}

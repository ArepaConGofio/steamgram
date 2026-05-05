package com.arepacongofio.steamgram.securization.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app.security")
public class AppSecurityProperties {

    private Jwt jwt = new Jwt();
    private Routes routes = new Routes();
    private List<User> users = new ArrayList<>();
    private Permissions permissions = new Permissions();

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public static class Jwt {
        private String secret;
        private int expirationMinutes = 120;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public int getExpirationMinutes() {
            return expirationMinutes;
        }

        public void setExpirationMinutes(int expirationMinutes) {
            this.expirationMinutes = expirationMinutes;
        }
    }

    public static class Routes {
        private List<String> publicRoutes = new ArrayList<>();
        private List<String> protectedRoutes = new ArrayList<>();

        public List<String> getPublic() {
            return publicRoutes;
        }

        public void setPublic(List<String> publicRoutes) {
            this.publicRoutes = publicRoutes;
        }

        public List<String> getProtected() {
            return protectedRoutes;
        }

        public void setProtected(List<String> protectedRoutes) {
            this.protectedRoutes = protectedRoutes;
        }
    }

    public static class User {
        private String username;
        private String password;
        private List<String> roles = new ArrayList<>();

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }

    public static class Permissions {
        private RolePermissions collaborator = new RolePermissions();
        private RolePermissions admin = new RolePermissions();

        public RolePermissions getCollaborator() {
            return collaborator;
        }

        public void setCollaborator(RolePermissions collaborator) {
            this.collaborator = collaborator;
        }

        public RolePermissions getAdmin() {
            return admin;
        }

        public void setAdmin(RolePermissions admin) {
            this.admin = admin;
        }
    }

    public static class RolePermissions {
        private boolean canCreate;
        private boolean canEditOwnDraftOrRejected;
        private boolean canEditAny;
        private boolean canSubmitForApproval;
        private boolean canApprove;
        private boolean canReject;
        private boolean canDelete;

        public boolean isCanCreate() {
            return canCreate;
        }

        public void setCanCreate(boolean canCreate) {
            this.canCreate = canCreate;
        }

        public boolean isCanEditOwnDraftOrRejected() {
            return canEditOwnDraftOrRejected;
        }

        public void setCanEditOwnDraftOrRejected(boolean canEditOwnDraftOrRejected) {
            this.canEditOwnDraftOrRejected = canEditOwnDraftOrRejected;
        }

        public boolean isCanEditAny() {
            return canEditAny;
        }

        public void setCanEditAny(boolean canEditAny) {
            this.canEditAny = canEditAny;
        }

        public boolean isCanSubmitForApproval() {
            return canSubmitForApproval;
        }

        public void setCanSubmitForApproval(boolean canSubmitForApproval) {
            this.canSubmitForApproval = canSubmitForApproval;
        }

        public boolean isCanApprove() {
            return canApprove;
        }

        public void setCanApprove(boolean canApprove) {
            this.canApprove = canApprove;
        }

        public boolean isCanReject() {
            return canReject;
        }

        public void setCanReject(boolean canReject) {
            this.canReject = canReject;
        }

        public boolean isCanDelete() {
            return canDelete;
        }

        public void setCanDelete(boolean canDelete) {
            this.canDelete = canDelete;
        }
    }
}
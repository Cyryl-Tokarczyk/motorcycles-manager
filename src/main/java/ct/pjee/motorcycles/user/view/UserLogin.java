package ct.pjee.motorcycles.user.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@RequestScoped
@Named
public class UserLogin {

    private final HttpServletRequest request;

    private final SecurityContext securityContext;

    private final FacesContext facesContext;

    @Inject
    public UserLogin(final HttpServletRequest request, final SecurityContext securityContext, final FacesContext facesContext) {
        this.request = request;
        this.securityContext = securityContext;
        this.facesContext = facesContext;
    }

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    public void loginAction() {
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(request, extractResponseFromFacesContext(), withParams().credential(credential));
        System.out.println(status);
//        System.out.println(securityContext.getCallerPrincipal());
//        System.out.println(request.getSession(true).getId());
        facesContext.responseComplete();

//        switch (status) {
//            case SUCCESS:
//                // Redirect to a protected resource or home page
//                try {
//                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/index.xhtml");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case SEND_CONTINUE:
//                // For mechanisms like FORM authentication, let the response continue
//                facesContext.responseComplete();
//                break;
//            case SEND_FAILURE:
//                // Show an error message
//                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid username or password"));
//                break;
//            case NOT_DONE:
//                // Do nothing, user likely canceled authentication
//                break;
//        }
    }

    private HttpServletResponse extractResponseFromFacesContext() {
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }

}

package lcristofolini.api_crud_venda_produto.config;

import lcristofolini.api_crud_venda_produto.exceptions.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    class MsgErro {
        String msgErro;

        public MsgErro(String msgErro) {
            this.msgErro = msgErro;
        }

        public String getMsgErro() {
            return msgErro;
        }

        public void setMsgErro(String msgErro) {
            this.msgErro = msgErro;
        }
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<MsgErro> handleBusinessRuleException(BusinessRuleException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new MsgErro(ex.getMessage()));
    }
}

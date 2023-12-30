package pt.iade.mypastry.webserver.services;

import pt.iade.mypastry.webserver.models.mails.MailDetail;
import pt.iade.mypastry.webserver.results.ConfirmKeyResponse;

public interface MailService {
    public ConfirmKeyResponse sendMail(MailDetail mailDetail);
}

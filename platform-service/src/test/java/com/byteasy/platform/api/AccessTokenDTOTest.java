package com.byteasy.platform.api;

import com.byteasy.common.util.JacksonUtil;
import com.byteasy.platform.dto.keycloak.AccessTokenDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
public class AccessTokenDTOTest {

    @Test
    public void test() {
        String json = "{\n"
                + "  \"access_token\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMeVdWb0VMRHBSazcyalVDbWZ6UkRIZ2ZWV2xzRnlaV0dOblNVN3dWMDhJIn0.eyJleHAiOjE3NDk3NzEzMjMsImlhdCI6MTc0OTc3MTAyMywianRpIjoiZjdlMGE2MzYtYmU5Yi00MDM2LThkYTAtNWEzODM4OTI5MWZhIiwiaXNzIjoiaHR0cDovL3Nzby1kZXYuc2Qtaml1eWFuZy5jb20vcmVhbG1zL3N0YWZmIiwic3ViIjoiZjpmYTFhMmNlYy05Njg2LTRiMDUtOTc4Zi01NGUzNzQ2NjYwYzY6MTMxMjE4IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYXBpc2l4Iiwic2lkIjoiNjNhYmQ4ZWUtZDc5Yy00OWRmLWJkNTktZDU0Y2NhZDJhNjc0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgcGhvbmUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IuW-kCDmt5HmnagiLCJwaG9uZV9udW1iZXIiOiIxNTMxNTA4NjI2NSIsInByZWZlcnJlZF91c2VybmFtZSI6IjEzMTIxOCIsImdpdmVuX25hbWUiOiLlvpAiLCJmYW1pbHlfbmFtZSI6Iua3keadqCJ9.ERYo6MDwCzlyLjo4H4qN1TEf0wY-hEoYJFuzvX4RLSykEEqUjqufzLGCU55hiUxBz4PtZmCRCPkcbgNwKUufxuosMOUgVe3mWwgxeXAP2UgHLX5rhJeJcEOSgD5XjD9KOU_jIEnP4zaK1W1F19fXJ2Y2VLxgVwdH9Fvy56rUOzOPuYIga9XrJwlY2JeJX2gUCi4CKR8fCsd3sT97F-AVYP08nv7dC-jqTm-ArdzxT-LbDUPyKSbcFDdtxy1rXP-QD5nb_HvvRLdbaNVTOIAR8aqZJCgggOoAflPU2OdsAglUkMeAIxvEtNInFNiSM-baxGHjN5Ufcsok5rQoubXUKQ\",\n"
                + "  \"expires_in\": 300,\n"
                + "  \"refresh_expires_in\": 86400,\n"
                + "  \"refresh_token\": \"eyJhbGciOiJIUzUxMiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlODBjYmQ4Ny0wM2NkLTQwMDItYWY4Ni00ZTk1ZjhlOWFmNjYifQ.eyJleHAiOjE3NDk4NTc0MjMsImlhdCI6MTc0OTc3MTAyMywianRpIjoiYjBhMmQzNDEtOTQwMC00Yjk1LWIzNTktZmMwODk1NjkyN2Y4IiwiaXNzIjoiaHR0cDovL3Nzby1kZXYuc2Qtaml1eWFuZy5jb20vcmVhbG1zL3N0YWZmIiwiYXVkIjoiaHR0cDovL3Nzby1kZXYuc2Qtaml1eWFuZy5jb20vcmVhbG1zL3N0YWZmIiwic3ViIjoiZjpmYTFhMmNlYy05Njg2LTRiMDUtOTc4Zi01NGUzNzQ2NjYwYzY6MTMxMjE4IiwidHlwIjoiUmVmcmVzaCIsImF6cCI6ImFwaXNpeCIsInNpZCI6IjYzYWJkOGVlLWQ3OWMtNDlkZi1iZDU5LWQ1NGNjYWQyYTY3NCIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgcGhvbmUgYWNyIHdlYi1vcmlnaW5zIGVtYWlsIGJhc2ljIn0.DeZeBBwpop7uw_TY7drk_b26Y4RzOIo43-cxHtvFHt--MwN70TDrRN6Flumdm0Br3bb7YwQpGFLTYWRPBntChQ\",\n"
                + "  \"token_type\": \"Bearer\",\n"
                + "  \"id_token\": \"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJMeVdWb0VMRHBSazcyalVDbWZ6UkRIZ2ZWV2xzRnlaV0dOblNVN3dWMDhJIn0.eyJleHAiOjE3NDk3NzEzMjMsImlhdCI6MTc0OTc3MTAyMywianRpIjoiM2U1ZmJiNjYtMDdhOS00Y2QzLWE2NTctNzdhODM2YjI0Nzg4IiwiaXNzIjoiaHR0cDovL3Nzby1kZXYuc2Qtaml1eWFuZy5jb20vcmVhbG1zL3N0YWZmIiwiYXVkIjoiYXBpc2l4Iiwic3ViIjoiZjpmYTFhMmNlYy05Njg2LTRiMDUtOTc4Zi01NGUzNzQ2NjYwYzY6MTMxMjE4IiwidHlwIjoiSUQiLCJhenAiOiJhcGlzaXgiLCJzaWQiOiI2M2FiZDhlZS1kNzljLTQ5ZGYtYmQ1OS1kNTRjY2FkMmE2NzQiLCJhdF9oYXNoIjoiOTNTdTg1cFdoOVRhVWtrVTdiTmdrdyIsImFjciI6IjEiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IuW-kCDmt5HmnagiLCJwaG9uZV9udW1iZXIiOiIxNTMxNTA4NjI2NSIsInByZWZlcnJlZF91c2VybmFtZSI6IjEzMTIxOCIsImdpdmVuX25hbWUiOiLlvpAiLCJmYW1pbHlfbmFtZSI6Iua3keadqCJ9.V03etbrly21MlwJOO-DyhOt7YY437eYnA09b2ivsm0kZmPCj0PXKGOTqaY-4o3f1gm2IE5Bgqb50tlMwmldW1LSfOPaBoD34qy1X4PucjK8srEUpYQxjSyNJTHCndb_tlATutTcvM6LbxMofebYjjinN66F-nR_Js8E9mP4QUqJbT1RucwY59MY28SWz8uEibV4YmEMk2DH4ENH3E0KMp9Cu-JdznzDxHj5CqcB8fphP6EBeR38z0sLdepSPmbyZgLxIpTjRr1FtwHUtqFzXf6E7zZna1avXispQL1CG0AXeFkE3lwLxgkbktD0TGKFhKQdkaX7mjjIdTUuZre4_oA\",\n"
                + "  \"not-before-policy\": 0,\n"
                + "  \"session_state\": \"63abd8ee-d79c-49df-bd59-d54ccad2a674\",\n"
                + "  \"scope\": \"openid profile phone email\"\n"
                + "}";

        AccessTokenDTO accessTokenDTO = JacksonUtil.parse(json, AccessTokenDTO.class);
        log.info(JacksonUtil.toJson(accessTokenDTO));
    }
}

package models.implementation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import models.Entity;

/**
 * @Created by Terrax on 28-Feb-2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HR extends Entity {
    private String hrFirstName;     // The first name of the HR.
    private String hrLastName;      // The last name of the HR.
    private String phone;           // The phone of the HR.
    private String hrEmail;         // The hrEmail of the HR.
    private int companyId;          // The Company ID which the HR is working for.
    private String companyName;     // The name of the Company which the HR is working for.

    /**
     * Default constructor.
     */
    public HR() { }

    /**
     * Custom constructor which builds the object based on the existing parameters.
     * @param node the details from which to create the new object.
     */
    public HR(JsonNode node) {
        if (node.has("hrCompany")) {
            int companyId = Integer.parseInt(node.get("hrCompany").textValue());
            setCompanyId(companyId);
        }

        if (node.has("hrFirstName")) {
            setHrFirstName(node.get("hrFirstName").textValue());
        }

        if (node.has("hrLastName")) {
            setHrLastName(node.get("hrLastName").textValue());
        }

        if (node.has("phone")) {
            setPhone(node.get("phone").textValue());
        }

        if (node.has("hrEmail")) {
            setHrEmail(node.get("hrEmail").textValue());
        }
    }

    /**
     * Custom constructor which accepts five parameters. It is used when building the result after search.
     * @param hrId the ID of the HR.
     * @param hrFirstName the first name of the HR.
     * @param hrLastName the last name of the HR.
     * @param phone the age of the Candidate.
     * @param hrEmail the hrEmail of the HR
     * @param companyName the name of the Company which the HR is working for.
     */
    public HR(int hrId, String hrFirstName, String hrLastName, String phone, String hrEmail, String companyName) {
        setId(hrId);
        setHrFirstName(hrFirstName);
        setHrLastName(hrLastName);
        setPhone(phone);
        setHrEmail(hrEmail);
        setCompanyName(companyName);
    }

    /**
     * Shows the first name of the HR.
     * @return the first name of the HR.
     */
    public String getHrFirstName() {
        return hrFirstName;
    }

    /**
     * Sets the first name of the HR.
     * @param hrFirstName the first name of the HR.
     */
    public void setHrFirstName(String hrFirstName) {
        this.hrFirstName = hrFirstName;
    }

    /**
     * Shows the last name of the HR.
     * @return the last name of the HR.
     */
    public String getHrLastName() {
        return hrLastName;
    }

    /**
     * Sets the last name of the HR.
     * @param hrLastName the last name of the HR.
     */
    public void setHrLastName(String hrLastName) {
        this.hrLastName = hrLastName;
    }

    /**
     * Shows the phone of the HR.
     * @return the phone of the HR.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone of the HR.
     * @param phone the phone of the HR.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Shows the hrEmail of the HR.
     * @return the hrEmail of the HR.
     */
    public String getHrEmail() {
        return hrEmail;
    }

    /**
     * Sets the hrEmail of the HR.
     * @param hrEmail the hrEmail of the HR.
     */
    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    /**
     * Shows the ID of the Company which the HR is working for.
     * @return the ID of the Company which the HR is working for.
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * Sets the ID of the Company which the HR is working for.
     * @param companyId the ID of the Company which the HR is working for.
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Shows the name of the Company which the HR is working for.
     * @return the name of the Company which the HR is working for..
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the name of the Company which the HR is working for.
     * @param companyName the name of the Company which the HR is working for..
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the details of the HR object and shows them.
     * @return the details of the HR.
     */
    @Override
    public String toString() {
        return "HR{" +
                "id='" + id + '\'' +
                "hrFirstName='" + hrFirstName + '\'' +
                ", hrLastName='" + hrLastName + '\'' +
                ", phone='" + phone + '\'' +
                ", hrEmail='" + hrEmail + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
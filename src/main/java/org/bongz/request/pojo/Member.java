package org.bongz.request.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Member {

    public ArrayList<PreferredCommunicationType> preferredCommunicationTypes;
    public String verbalLanguageCd;
    public String writtenLanguageCd;
    public String titleCd;
    public String sourceCd;
    public String initials;
    public String firstName;
    public String middleName;
    public String lastName;
    public String knownAsName;
    public NationalityStatus nationalityStatus;
    public ArrayList<Address> addresses;
    public ArrayList<Phone> phones;
    public ArrayList<EmailAddress> emailAddresses;
    public ArrayList<PersonMaritalStatus> personMaritalStatuses;
    public ArrayList<PersonUID> personUID;
    public ArrayList<PersonGender> personGenders;
    public ArrayList<Product> products;
    public String dateOfBirth;
}

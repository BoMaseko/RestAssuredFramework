package org.bongz.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.bongz.enums.Gender;
import org.bongz.enums.Nationality;


//Thanks to https://github.com/talifhani/za-id-validator-java/blob/master/talifhani/zaidvalidator/IDNumberData.java

public class IDNumberParserUtils {

	private LocalDate dateOfBirth;
	private int genderNum;
    private int citizenshipNum;
    private int checkBit;
    private Year pivotYear;
    private String idNumber;
    
    public static final DateTimeFormatter getTwoYearFormatter(Year pivotYear) {
    	return new DateTimeFormatterBuilder()
    			.appendValueReduced(ChronoField.YEAR, 2, 2, pivotYear.getValue())
    			.toFormatter();
    }
    
    private void breakDownIDNumber() {
    	String birthDate = idNumber.substring(0, 6);
    	
    	if(pivotYear == null) {
    		pivotYear = Year.of(Year.now().getValue() - 100);// Assume ID belongs to someone not older than 100 years
    	}
    	
    	int year = Year.parse(birthDate.substring(0, 2), IDNumberParserUtils.getTwoYearFormatter(pivotYear)).getValue();
    	
    	this.dateOfBirth = LocalDate.of(
    			year,
    			Month.of(Integer.parseInt(birthDate.substring(2, 4))),
    			Integer.parseInt(birthDate.substring(4)));
    	
    	this.genderNum       = Integer.parseInt(idNumber.substring(6, 10));
        this.citizenshipNum  = Integer.parseInt(idNumber.substring(10, 11));
        this.checkBit        = Integer.parseInt(idNumber.substring(12, 13));
    }
    
    public IDNumberDataUtils  parse(String idNumber) throws Exception {
    	if (idNumber.length() != 13) {
            throw new Exception("ID Length invalid: ZA ID Number must be 13 digits long");
        }

        this.idNumber = idNumber;

        this.breakDownIDNumber();

        return new IDNumberDataUtils(
            idNumber,
            this.dateOfBirth,
            this.genderNum >= 5000 ? Gender.MALE : Gender.FEMALE,
            this.citizenshipNum == 0 ? Nationality.SOUTHAFRICAN : (this.citizenshipNum == 1 ? Nationality.NONSOUTHAFRICAN : Nationality.REFUGEE),
            this.checkBit == this.calculateCheckBit()
        );
    }
    
    private int calculateCheckBit()
    {
        String withoutChecksum = idNumber.substring(0, idNumber.length() - 1);
		return luhnGenerator(withoutChecksum);
    }
    
    public IDNumberParserUtils setPivotYear(Year pivotYear)
    {
        this.pivotYear = pivotYear;

        return this;
    }
    
    public static int luhnGenerator(String number)
	{
		// if ( ! ctype_digit($number))
		// 	throw new Exception("Number can only have digits");

		int length = number.length();
		int sum    = 0;
        int weight = 2;
        char[] numberArray = number.toCharArray();

		for (int i = length - 1; i >= 0; i--)
		{
			int digit = weight * (numberArray[i] - '0');
			sum += Math.floor(digit / 10) + digit % 10;
			weight = weight % 2 + 1;
		}

		return (10 - sum % 10) % 10;
	}
}

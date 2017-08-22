package com.company;

/**
 * Created by anasimonenko1 on 22.08.17.
 */
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static  {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main (String[] args) {
        IncomeDataAdapter incomeDataAdapter = new IncomeDataAdapter(new IncomeData()
        {
            @Override
            public String getCountryCode()
            {
                return "RU";
            }

            @Override
            public String getCompany()
            {
                return "JavaRush Ltd.";
            }

            @Override
            public String getContactFirstName()
            {
                return "Ivan";
            }

            @Override
            public String getContactLastName()
            {
                return "Ivanov";
            }

            @Override
            public int getCountryPhoneCode()
            {
                return 38;
            }

            @Override
            public int getPhoneNumber()
            {
                return 501234567;
            }
        });

        System.out.println(incomeDataAdapter.getName());
        System.out.println(incomeDataAdapter.getPhoneNumber());
        System.out.println(incomeDataAdapter.getCompanyName());
        System.out.println(incomeDataAdapter.getCountryName());
    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        IncomeDataAdapter(IncomeData incomeData)
        {
            this.data = incomeData;
        }

        @Override
        public String getName()
        {
            return this.data.getContactLastName()+", "+this.data.getContactFirstName();
        }

        @Override
        public String getPhoneNumber()
        {
            String phoneNumber = this.data.getPhoneNumber() + "";
            String nulls = "";
            if (phoneNumber.length()<10) {
                for (int i =0; i < (10-phoneNumber.length()); i++)
                {
                    nulls = nulls+"0";
                }
            }
            phoneNumber = nulls + phoneNumber;
            String firstPart = phoneNumber.substring(0, 3);
            String secondPart = phoneNumber.substring(3, 6);
            String thirdPart = phoneNumber.substring(6, 8);
            String fourthPart = phoneNumber.substring(8);
            return "+"+this.data.getCountryPhoneCode()+"("+firstPart+")"+secondPart+"-"+thirdPart+"-"+fourthPart;
        }

        @Override
        public String getCompanyName()
        {
            return this.data.getCompany();
        }

        @Override
        public String getCountryName()
        {
            return countries.get(this.data.getCountryCode());
        }
    }

    public static interface IncomeData {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        int getCountryPhoneCode();      //example 38
        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}
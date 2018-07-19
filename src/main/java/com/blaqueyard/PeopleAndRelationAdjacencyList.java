//package com.blaqueyard;
//
///**
// * Created by admin on 7/11/18.
// */
//
//import java.util.LinkedList;
//
///**
// * Fredrick Oluoch
// * http://www.blaqueyard.com
// * 0720106420 | 0722508906
// * email: menty44@gmail.com
// */
//
//
//public class PeopleAndRelationAdjacencyList {
//
//    private static String MALE = "male";
//    private static String FEMALE = "female";
//
//    public static void main(String[] args) {
//        int size = 35;
//        LinkedList<Person> adjListArray[] = new LinkedList[size];
//        for (int i = 0; i < size; i++) {
//            adjListArray[i] = new LinkedList<>();
//        }
//
//        addPerson( adjListArray, "GGM1", MALE, null, null );
//        addPerson( adjListArray, "GGF1", FEMALE, null, null );
//
//        addPerson( adjListArray, "GM1", MALE, "0720106420", "0720000000" );
//        addPerson( adjListArray, "GM2", MALE, "0720106420", "0720000000" );
//
//        addPerson( adjListArray, "GM1W", FEMALE, null, null );
//        addPerson( adjListArray, "GM2W", FEMALE, null, null );
//
//        addPerson( adjListArray, "PM1", MALE, "GM1", "GM1W" );
//        addPerson( adjListArray, "PM2", MALE, "GM1", "GM1W" );
//        addPerson( adjListArray, "PM3", MALE, "GM2", "GM2W" );
//
//        //my hack
//        addPerson( adjListArray, "PM4", MALE, "GM2", "GM2W" );
//
//        addPerson( adjListArray, "PM1W", FEMALE, null, null );
//        addPerson( adjListArray, "PM2W", FEMALE, null, null );
//        addPerson( adjListArray, "PM3W", FEMALE, null, null );
//
//        addPerson( adjListArray, "S1", MALE, "PM1", "PM1W" );
//        addPerson( adjListArray, "S2", MALE, "PM2", "PM2W" );
//        addPerson( adjListArray, "S3", MALE, "PM3", "PM3W" );
//        addPerson( adjListArray, "S4", MALE, "PM3", "PM3W" );
//
//        printGraph(adjListArray);
//        System.out.println("Done !");
//
//
//        getRelationBetweenPeopleForGivenNames(adjListArray, "S3", "S4");
//        getRelationBetweenPeopleForGivenNames(adjListArray, "S1", "S2");
//
//
//        getRelationBetweenPeopleForGivenNames(adjListArray, "PM3", "PM4");
//        getRelationBetweenPeopleForGivenNames(adjListArray, "PM1", "PM2");
//
//    }
//
//
//    private static void addPerson(LinkedList<Person>[] adjListArray, String name, String gender, String fatherName, String motherName) {
//        Person person = new Person(name, gender, 0, 0, fatherName, motherName);
//        int indexToPutperson = getEmptyIndexInAdjListToInserterson(adjListArray);
//        adjListArray[indexToPutperson].addLast(person);
//        if( fatherName!=null ){
//            int indexOffatherName = getIndexOfGivenNameInHeadPositionOfList( adjListArray, fatherName);
//            adjListArray[indexOffatherName].addLast(person);
//        }
//        if( motherName!=null ){
//            int indexOfMotherName = getIndexOfGivenNameInHeadPositionOfList( adjListArray, motherName);
//            adjListArray[indexOfMotherName].addLast(person);
//        }
//    }
//
//
//    private static void getRelationBetweenPeopleForGivenNames(LinkedList<Person>[] adjListArray, String name1, String name2) {
//
//        if ( adjListArray[getIndexOfGivenNameInHeadPositionOfList(adjListArray, name1)].peekFirst().fatherName
//                .equalsIgnoreCase(
//                        adjListArray[getIndexOfGivenNameInHeadPositionOfList(adjListArray, name2)].peekFirst().fatherName) ) {
//            System.out.println("SIBLIGS");
//            return;
//        }
//
//        String name1FatherName = adjListArray[getIndexOfGivenNameInHeadPositionOfList(adjListArray, name1)].peekFirst().fatherName;
//        String name2FatherName = adjListArray[getIndexOfGivenNameInHeadPositionOfList(adjListArray, name2)].peekFirst().fatherName;
//
//        if ( adjListArray[getIndexOfGivenNameInHeadPositionOfList(adjListArray, name1FatherName)].peekFirst().fatherName
//                .equalsIgnoreCase(
//                        adjListArray[getIndexOfGivenNameInHeadPositionOfList(adjListArray, name2FatherName)].peekFirst().fatherName) ) {
//            System.out.println("COUSINS");
//        }
//    }
//
//
//    private static int getIndexOfGivenNameInHeadPositionOfList( LinkedList<Person>[] adjListArray, String nameToBeSearched ) {
//        for (int i = 0; i < adjListArray.length; i++) {
//            if( adjListArray[i] != null ){
//                if(adjListArray[i].peekFirst() != null){
//                    if(adjListArray[i].peekFirst().name.equalsIgnoreCase(nameToBeSearched)){
//                        return i;
//                    }
//                }
//            }
//        }
//        // handle if father name is not found
//        return 0;
//    }
//
//
//    private static void printGraph(LinkedList<Person>[] adjListArray) {
//        for (int v = 0; v < 15; v++) {
//            System.out.print("head");
//
//            LinkedList<Person> innerLinkedList = adjListArray[v];
//            for (int i = 0; i < innerLinkedList.size(); i++) {
//                Person person = innerLinkedList.get(i);
//                System.out.print(" -> " + person.name);
//            }
//
//            System.out.println("\n");
//        }
//    }
//
//    private static int getEmptyIndexInAdjListToInserterson( LinkedList<Person>[] adjListArray) {
//        for (int i = 0; i < adjListArray.length; i++) {
//            if(adjListArray[i].isEmpty()){
//                return i;
//            }
//        }
//        throw new IndexOutOfBoundsException("List of relation is full.");
//    }
//}

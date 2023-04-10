package a1_BI12_170;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;

/**
 * @overview A student being that is characterized by an id, name, phoneNumber and address.
 * @attributes
 *  <pre>
 *  <b>list of attributes:</b>
 *      id              Integer     int
 *      name            String      
 *      phoneNumber     String      
 *      address         String      
 *  </pre>
 * @object A typical Student is <i,s,p,a>, where <i,s,p,a> is the id, name, phoneNumber and address of the student.
 * 
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\ max(id)=1e9 /\
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * mutable(address)=true /\ optional(address)=false /\ length(address)=100
 * 
 * @author bhhoang
 */

public class Student implements Comparable<Student>{
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    private Integer id;
    @DomainConstraint(type = "String", mutable = true, optional = false, length = MAX_NAME_LENGTH)
    protected String name;
    @DomainConstraint(type = "Integer", mutable = true, optional = false, length = MAX_PHONE_NUMBER_LENGTH)
    protected String phoneNumber;
    @DomainConstraint(type = "Integer", mutable = true, optional = false, length = MAX_ADDRESS_LENGTH)
    protected String address;

    // Constants
    public static final int MIN_ID = 1;
    public static final int MAX_ID = (int) 1e9;
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_PHONE_NUMBER_LENGTH = 10;
    public static final int MAX_ADDRESS_LENGTH = 100;


    public Student(@AttrRef("id") Integer id,
                   @AttrRef("name") String name, 
                   @AttrRef("phoneNumber") String phoneNumber, 
                   @AttrRef("address") String address) throws NotPossibleException {
        if (!validateId(id)) {
            throw new NotPossibleException("Student.init: invalid id: " + id);
        }
        if (!validateName(name)) {
            throw new NotPossibleException("Student.init: invalid name: " + name);
        }
        if (!validatePhoneNumber(phoneNumber)) {
            throw new NotPossibleException("Student.init: invalid phoneNumber: " + phoneNumber);
        }
        if (!validateAddress(address)) {
            throw new NotPossibleException("Student.init: invalid address: " + address);
        }
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * @effects
     *  Overload default constructor with no parameter
     * 
     */
    public Student() {}

    /**
     * @effects
     *  if id is valid
     *      return true
     *  else
     *     return false
     * @param id
     */
    @DOpt(type=OptType.Helper)
    protected boolean validateId(Integer id) {
        return id >= MIN_ID && id <= MAX_ID;
    }

    /**
     * @effects
     *  if name is valid
     *      return true
     *  else
     *     return false
     * @param name
     */
    @DOpt(type=OptType.Helper)
    protected boolean validateName(String name) {
        return name != null && name.length() > 0;
    }

    /**
     * @effects
     *  if phoneNumber is valid
     *      return true
     *  else
     *     return false
     * @param phoneNumber
     */
    @DOpt(type=OptType.Helper)
    protected boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.length() > 0;
    }

    /**
     * @effects
     *  if address is valid
     *      return true
     *  else
     *     return false
     * @param address
     */
    @DOpt(type=OptType.Helper)
    protected boolean validateAddress(String address) {
        return address != null && address.length() > 0;
    }

    /**
     * @effects
     *  return id
     * @return id
     */
    @DOpt(type=OptType.Observer)
    @AttrRef("id")
    public Integer getId() {
        return id;
    }
    
    /**
     * @effects
     *  Get student's name
     * @return name
     */
    @DOpt(type=OptType.Observer)
    @AttrRef("name")
    public String getName() {
        return name;
    }

    /**
     * @effects
     *  return phoneNumber
     * @return phoneNumber
     */
    @DOpt(type=OptType.Observer)
    @AttrRef("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @effects
     *  return address
     */
    @DOpt(type=OptType.Observer)
    @AttrRef("address")
    public String getAddress() {
        return address;
    }

    /**
     *  @modifies this.name
     *  @effects
     *      if name is valid
     *          set this.name = name
     *      else
     *          do nothing
     */
    @DOpt(type=OptType.Mutator)
    @AttrRef("name")
    public void setName(String name) {
        if (validateName(name)) {
            this.name = name;
        }
    }

    /**
     *  @modifies this.phoneNumber
     *  @effects
     *      if phoneNumber is valid
     *          set this.phoneNumber = phoneNumber
     *      else
     *          do nothing
     */
    @DOpt(type=OptType.Mutator)
    @AttrRef("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
    }

    /**
     *  @modifies this.address
     *  @effects
     *      if address is valid
     *          set this.address = address
     *      else
     *          do nothing
     */
    @DOpt(type=OptType.Mutator)
    @AttrRef("address")
    public void setAddress(String address) {
        if (validateAddress(address)) {
            this.address = address;
        }
    }

    /**
     * @effects
     *  return true if this satisfies the abstract properties
     *          false otherwise
     */

    public boolean repOK() {
        return validateId(id) && validateName(name) && validatePhoneNumber(phoneNumber) && validateAddress(address);
    }

    /**
     * @effects
     *  return a string representation of this object
     */
    @Override
    public String toString() {
        return "Student(" + id + "," + name + "," + phoneNumber + "," + address + ")";
    }

    /**
     * @effects compare two students by name
     * @param Student student
     * @return Integer
     */
    @Override
    @DOpt(type=OptType.Other)
    @AttrRef("name")
    public int compareTo(Student student) {
        return name.compareTo(student.getName());
    }

}
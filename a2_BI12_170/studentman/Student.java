package a2_BI12_170.studentman;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;

/**
 * @overview 
 * A student being that is characterized by an id, name, phoneNumber and address.
 * 
 * @attributes
 *  id              Integer     int
 *  name            String
 *  phoneNumber     String
 *  address         String
 * 
 * @object 
 * A typical Student is <i,s,p,a>, where <i,s,p,a> is the id, name, phoneNumber and address of the student.
 * 
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\ max(id)=1e9 /\
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * mutable(address)=true /\ optional(address)=false /\ length(address)=100
 * 
 * @author bhhoang
 */

public class Student implements Comparable<Student>, Document{
    @DomainConstraint(type = "int", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    protected int id;

    @DomainConstraint(type = "String", optional = false, length = MAX_NAME_LENGTH)
    protected String name;

    @DomainConstraint(type = "int", optional = false, length = MAX_PHONE_NUMBER_LENGTH)
    protected String phoneNumber;

    @DomainConstraint(type = "int", mutable = true, optional = false, length = MAX_ADDRESS_LENGTH)
    protected String address;

    // Constants
    public static final int MIN_ID = 1;
    public static final int MAX_ID = (int) 1e9;
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_PHONE_NUMBER_LENGTH = 10;
    public static final int MAX_ADDRESS_LENGTH = 100;

    /**
     * @effects
     * if id, name, phoneNumber and address are valid
     *     initialise instance of this class as <id, name, phoneNumber, address>
     * else
     *    throw NotPossibleException
     */
    public Student(@AttrRef("id") int id,
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
     *  Overload default constructor with no parameter for subclasses of Student without using super()
     * @example
     *  <pre>
     *     <p>Assumming given code in UndegradStudent is subclass of Student</p>
     *      <code>
     *      public class UndergradStudent extends Student {
     *         private int id;
     *         public UndergradStudent(id, name, phoneNumber, address) {
     *            this.id = id;
     *            this.name = name;
     *            this.phoneNumber = phoneNumber;
     *        }
     *     </code> 
     *    <p>With no overload constructor, the above code got error: Implicit super constructor Student() is undefined. Must explicitly invoke another constructorJava(134217871)</p>
     *    <p>Else, it's working fine.</p>
     * </pre>
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
    @DOpt(type=OptType.Default)
    protected boolean validateId(int id) {
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
    @DOpt(type=OptType.Default)
    protected boolean validateName(String name) {
        return name != null && name.length() > 0 && name.length() <= MAX_NAME_LENGTH;
    }

    /**
     * @effects         
     *  if phoneNumber is valid
     *      return true
     *  else
     *     return false
     * @param phoneNumber
     */
    @DOpt(type=OptType.Default)
    protected boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.length() > 0 && phoneNumber.length() <= MAX_PHONE_NUMBER_LENGTH;
    }

    /**
     * @effects
     *  if address is valid
     *      return true
     *  else
     *     return false
     * @param address
     */
    @DOpt(type=OptType.Default)
    protected boolean validateAddress(String address) {
        return address != null && address.length() > 0 && address.length() <= MAX_ADDRESS_LENGTH;
    }

    /**
     * @effects
     *  return id
     * @return id
     */
    @DOpt(type=OptType.Observer)
    @AttrRef("id")
    public int getId() {
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
    public boolean setName(String name) {
        if (validateName(name)) {
            this.name = name;
            return true;
        }
        return false;
    }

    /**
     *  @modifies this.phoneNumber
     *  @effects
     *      if phoneNumber is valid
     *          set this.phoneNumber = phoneNumber
     *          return true
     *      else
     *          returrn false
     */
    @DOpt(type=OptType.Mutator)
    @AttrRef("phoneNumber")
	public boolean setPhoneNumber(String phoneNumber) {
		if (validatePhoneNumber(phoneNumber)) {
			this.phoneNumber=phoneNumber;
			return true;
		} else {
			return false;
		}
	}
	

    /**
     *  @modifies this.address
     *  @effects
     *      if address is valid
     *          set this.address = address
     *          return true
     *      else
     *          returrn false
     */
    @DOpt(type=OptType.Mutator)
    @AttrRef("address")
    public boolean setAddress(String address) {
        if (validateAddress(address)) {
            this.address = address;
            return true;
        }
        return false;
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
		return String.format("Student: <%d, %s, %s, %s>",id,name,phoneNumber,address);
	}

    /**
     * @param student Student instance to be compared
     * @effects
     *  if student is null
     *      throw NullPointerException
     *  else if student is not instance of Student
     *     throw ClassCastException
     *  else
     *     return this.name.compareTo(Student.name)
     * 
     * @return this.name.compareTo(Student.name)
     */
    @Override
    @DOpt(type=OptType.Default)
    @AttrRef("name")
    public int compareTo(Student student) throws NullPointerException, ClassCastException {
        if (student == null) {
            throw new NullPointerException("Student.compareTo: student is null");
        }
        if (!(student instanceof Student)) {
            throw new ClassCastException("Student.compareTo: student is not instance of Student");
        }
        return this.name.compareTo(student.name);
    }

    /**
     * @effects
     * return a HTML doc of this student
     * e.g.:
     * <pre>
     * <code>
     * <html>
     * <head><title>Student: 170-Bui Huy Hoang</title></head>
     * <body>
     * 170 Bui Huy Hoang SDT Dia chi</body></html>
     * </code>
     * @return HTML doc of this student
     */
    @Override
    @DOpt(type = OptType.Default)
    public String toHtmlDoc(){
        return "<html>\n" +
                "<head>"+
                "<title>Student:" + getId() + "-" + getName() +"</title>" +
                "</head>" +
                " <body>\n" +
                getId()+ " " + getPhoneNumber() + " " + getAddress() + " " +
                " </body>" +
                "</html>";
    }

}
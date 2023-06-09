package a2_BI12_170.studentman;

import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;


/**
 * @overview 
 * A postgraduate student being that is characterized by an id, name, phoneNumber, address and gpa.
 * 
 * @attributes
 *      id              Integer     int
 *      name            String
 *      phoneNumber     String
 *      address         String
 *      gpa             Float
 *   
 * @object 
 *  A typical PostgradStudent is <i,s,p,a,g>, where <i,s,p,a,g> is the id, name, phoneNumber, address and gpa of the postgraduate student.
 * 
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=1e8+1 /\ max(id)=1e9 /\
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * mutable(address)=true /\ optional(address)=false /\ length(address)=100 /\
 * mutable(gpa)=true /\ optional(gpa)=false /\ min(gpa)=0.0 /\ max(gpa)=4.0
 * 
 * @author bhhoang
 */

public class PostgradStudent extends Student{
    // Constants
    public static final int MIN_ID = (int) 1e8 + 1;
    public static final float MIN_GPA =  0.0f;
    public static final float MAX_GPA =  4.0f;
    
    @DomainConstraint(type = "Float", optional = false, min = MIN_GPA, max = MAX_GPA)
    private float gpa;

    // Constructor
    /**
     * @effects 
     *  if id, name, phoneNumber, address and gpa are valid
     *      initialise this as <id, name, phoneNumber, address, gpa>
     *  else
     *      print error message
     * @throws NotPossibleException if id, name, phoneNumber, address and gpa are invalid
     */
    public PostgradStudent(@AttrRef("id") int id,
                           @AttrRef("name") String name, 
                           @AttrRef("phoneNumber") String phoneNumber, 
                           @AttrRef("address") String address,
                           @AttrRef("gpa") float gpa) throws NotPossibleException {
        super(id, name, phoneNumber, address);
        if (!validateGpa(gpa)) {
            throw new NotPossibleException("PostgradStudent.init: invalid gpa: " + gpa);
        }
        this.gpa = gpa;
    }

    // Methods
    /**
     * @effects 
     *  if gpa is valid
     *      return true
     *  else
     *      return false
     */
    public static boolean validateGpa(float gpa) {
        return gpa >= MIN_GPA && gpa <= MAX_GPA;
    }

    /**
     * @effects
     *   if gpa is valid
     *      set this.gpa = gpa
     *      return true
     *   else
     *     return false
     */
    @DOpt(type = OptType.Mutator)
    public boolean setGpa(float gpa) {
        if (validateGpa(gpa)) {
            this.gpa = gpa;
            return true;
        }
        return false;
    }

    /**
     * @effects 
     *  return gpa
     */
    @DOpt(type = OptType.Observer)
    public float getGpa() {
        return gpa;
    }

    @Override
    public boolean repOK() {
        return super.repOK() && validateGpa(gpa);
    }

    @Override
    public String toString() {
        return "PostgradStudent<" + getId() + ", " + getName() + ", " + getPhoneNumber() + ", " + getAddress() + ", " + gpa + ">";
    }

    @Override
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    public boolean validateId(int id) {
        return id >= MIN_ID && id <= MAX_ID;
    }

    @Override
    @DOpt(type = OptType.Default)
    public String toHtmlDoc(){
        return "<html>\n" +
                "<head>"+
                "<title>Student:" + getId() + "-" + getName() +"</title>" +
                "</head>" +
                " <body>\n" +
                getId()+ " " + getPhoneNumber() + " " + getAddress() + " " + getGpa() +
                "</body>" +
                "</html>";
    }

}
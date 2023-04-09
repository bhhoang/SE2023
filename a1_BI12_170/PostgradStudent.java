package a1_BI12_170;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.AttrRef;
import utils.DOpt;
import utils.OptType;


/**
 * @overview A postgraduate student being that is characterized by an id, name, phoneNumber, address and gpa.
 * @attributes
 * <pre>
 * <b>list of attributes:</b>
 *      id              Integer     int
 *      name            String
 *      phoneNumber     String
 *      address         String
 *      gpa             Float
 * </pre>
 * 
 * @object A typical PostgradStudent is <i,s,p,a,g>, where <i,s,p,a,g> is the id, name, phoneNumber, address and gpa of the postgraduate student.
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
    
    @DomainConstraint(type = "Float", mutable = true, optional = false, min = MIN_GPA, max = MAX_GPA)
    private Float gpa;

    public PostgradStudent(@AttrRef("id") Integer id,
                           @AttrRef("name") String name, 
                           @AttrRef("phoneNumber") String phoneNumber, 
                           @AttrRef("address") String address,
                           @AttrRef("gpa") Float gpa) {
        super(id, name, phoneNumber, address);
        if (!validateGpa(gpa)) {
            throw new NotPossibleException("PostgradStudent.init: invalid gpa: " + gpa);
        }
        if (!validateId(id)) {
            throw new NotPossibleException("PostgradStudent.init: invalid id: " + id);
        }
        if (!validateName(name)) {
            throw new NotPossibleException("PostgradStudent.init: invalid name: " + name);
        }
        if (!validatePhoneNumber(phoneNumber)) {
            throw new NotPossibleException("PostgradStudent.init: invalid phoneNumber: " + phoneNumber);
        }
        if (!validateAddress(address)) {
            throw new NotPossibleException("PostgradStudent.init: invalid address: " + address);
        }
        this.gpa = gpa;        
    }

    /**
     * @effects
     *  if gpa is valid
     *      return true
     *  else
     *     return false
     * @param gpa
     */
    @DOpt(type=OptType.Helper)
    protected boolean validateGpa(Float gpa) {
        return gpa >= MIN_GPA && gpa <= MAX_GPA;
    }

}
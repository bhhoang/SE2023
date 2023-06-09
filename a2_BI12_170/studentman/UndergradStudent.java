package a2_BI12_170.studentman;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview An under graduate student being that is characterized by an id, name, phoneNumber and address.
 * @attributes
 * <pre>
 * <b>list of attributes:</b>
 *     id              Integer     int
 *     name            String
 *     phoneNumber     String
 *     address         String
 * </pre>
 * 
 * @object A typical UndergradStudent is <i,s,p,a>, where <i,s,p,a> is the id, name, phoneNumber and address of the student.
 * 
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=1e5 /\ max(id)=1e8 /\
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 * mutable(address)=true /\ optional(address)=false /\ length(address)=100
 * 
 * @author bhhoang
 */
public class UndergradStudent extends Student{
    //Override constants
    public static final int MIN_ID = (int) 1e5;
    public static final int MAX_ID = (int) 1e8;

    // Constructor
    /**
     * @effects 
     *  if id, name, phoneNumber and address are valid
     *      initialise instance of this class as <id, name, phoneNumber, address>
     * @throws
     *  NotPossibleException if id, name, phoneNumber and address are invalid
     */
    public UndergradStudent(@AttrRef("id") int id,
                            @AttrRef("name") String name, 
                            @AttrRef("phoneNumber") String phoneNumber, 
                            @AttrRef("address") String address) throws NotPossibleException {
        super(id, name, phoneNumber, address);
    }

    // Override methods
    /**
     * @effects 
     *  if id is valid
     *      return true
     *  else
     *      return false
     */
    @Override
    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    protected boolean validateId(int id) {
        return id >= MIN_ID && id <= MAX_ID;
    }

    @Override
    public String toString(){
        return "UndergradStudent<" + getId() + "," + getName() + "," + getPhoneNumber() + "," + getAddress() + ">";
    }
    
    @DOpt(type=OptType.Default)
    @Override
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

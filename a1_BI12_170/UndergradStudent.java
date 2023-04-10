package a1_BI12_170;

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

    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    private Integer id;
    public UndergradStudent(@AttrRef("id") Integer id,
                            @AttrRef("name") String name, 
                            @AttrRef("phoneNumber") String phoneNumber, 
                            @AttrRef("address") String address) throws NotPossibleException {
        if (!validateId(id)) {
            throw new NotPossibleException("UndergradStudent.init: invalid id: " + id);
        }
        
        if (!validateName(name)) {
            throw new NotPossibleException("UndergradStudent.init: invalid name: " + name);
        }

        if (!validatePhoneNumber(phoneNumber)) {
            throw new NotPossibleException("UndergradStudent.init: invalid phoneNumber: " + phoneNumber);
        }

        if (!validateAddress(address)) {
            throw new NotPossibleException("UndergradStudent.init: invalid address: " + address);
        }

        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    /**
     * @effects
     *  if id is valid
     *      return true
     *  else
     *      return false
     */
    @DOpt(type = OptType.Helper)
    public boolean validateId(Integer id) {
        return id >= MIN_ID && id <= MAX_ID;
    }
}
